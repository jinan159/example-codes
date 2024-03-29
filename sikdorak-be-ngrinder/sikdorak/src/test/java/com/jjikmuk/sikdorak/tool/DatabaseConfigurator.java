package com.jjikmuk.sikdorak.tool;

import com.jjikmuk.sikdorak.review.command.domain.Review;
import com.jjikmuk.sikdorak.review.command.domain.ReviewRepository;
import com.jjikmuk.sikdorak.store.command.domain.Address;
import com.jjikmuk.sikdorak.store.command.domain.Store;
import com.jjikmuk.sikdorak.store.command.domain.StoreRepository;
import com.jjikmuk.sikdorak.user.user.command.domain.Authority;
import com.jjikmuk.sikdorak.user.user.command.domain.User;
import com.jjikmuk.sikdorak.user.user.command.domain.UserRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfigurator implements InitializingBean {

	@Autowired
	public DataGenerator generator;
	public Store store;
	public User kukim;
	public User jay;
	public User forky;
	public User hoi;
	public User rumka;
	public User admin;
	public String kukimValidAuthorizationHeader;
	public String jayValidAuthorizationHeader;
	public String followSendUserValidAuthorizationHeader;
	public String followAcceptUserValidAuthorizationHeader;
	public String kukimRefreshToken;
	public String kukimExpiredRefreshToken;
	public String kukimInvalidRefreshToken;
	public Review kukimPublicReview;
	public Review followAcceptUserPublicReview;
	public Review followAcceptUserProtectedReview;
	public Review followAcceptUserPrivateReview;
	public String kukimImageFileName = "abcde-1234-testimage-jjikmuk.png";
	@PersistenceContext
	private EntityManager entityManager;
	private List<String> tableNames;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewRepository reviewRepository;

	public void initDataSource() {
		initStoreData();
		initBasicUserData();
		initFollowingUserData();
		initUserAuthorizationData();
		initReviewData();
		postReviews();
		likeReview();
	}

	public void clear() {
		entityManager.unwrap(Session.class).doWork(this::cleanUpDatabase);
	}

	@Override
	public void afterPropertiesSet() {
		entityManager.unwrap(Session.class).doWork(this::extractTableNames);
	}

	// reference : https://www.baeldung.com/jdbc-database-metadata
	private void extractTableNames(Connection connection) throws SQLException {
		List<String> tableNames = new ArrayList<>();

		ResultSet tables = connection
			.getMetaData()
			.getTables(connection.getCatalog(), null, "%", new String[]{"TABLE"});

		try (tables) {
			while (tables.next()) {
				tableNames.add(tables.getString("table_name"));
			}

			this.tableNames = tableNames;
		}
	}

	private void cleanUpDatabase(Connection connection) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");

			for (String tableName : tableNames) {
				statement.executeUpdate("TRUNCATE TABLE " + tableName);
			}

			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
		}
	}

	private void initStoreData() {
		Address address = Address.requiredFieldBuilder("서울시 송파구 11-22", "서울시 송파구 좋은길1")
			.build();

		this.store = storeRepository.save(new Store(
			123123L,
			"맛있는가게",
			"02-0000-0000",
			address,
			127.105143,
			37.5093890));

		this.store = storeRepository.save(new Store(
			456456L,
			"노원 쉑쉑버거",
			"02-0000-0000",
			Address.requiredFieldBuilder("상계동 713 롯데백화점 노원점 지상 1층", "서울 노원구 동일로 1414")
				.build(),
			127.0615,
			37.6547));

		this.store = storeRepository.save(new Store(
			12356L,
			"와플대학 노원캠퍼스",
			"02-0000-0000",
			Address.requiredFieldBuilder("상계동 713 하하하", "서울 노원구 몰라몰라")
				.build(),
			127.0607,
			37.653));

		this.store = storeRepository.save(new Store(
			12345L,
			"위안바오",
			"02-0000-0000",
			Address.requiredFieldBuilder("상계동 713 노원 어딘가", "서울 노원구 동일로 동동동")
				.build(),
			127.0604,
			37.656));
	}

	private void initBasicUserData() {
		this.kukim = userRepository.save(
			new User(1000000L, "쿠킴", "https://s3.ap-northeast-2.amazonaws.com/user/kukim.jpg",
				"kukim@gmail.com"));
		this.jay = userRepository.save(
			new User(2000000L, "제이", "https://s3.ap-northeast-2.amazonaws.com/user/jay.jpg",
				"jay@gmail.com"));
		this.forky = userRepository.save(
			new User(3000000L, "포키", "https://s3.ap-northeast-2.amazonaws.com/user/forky.jpg",
				"forky@gmail.com"));
		this.hoi = userRepository.save(
			new User(4000000L, "호이", "https://s3.ap-northeast-2.amazonaws.com/user/hoi.jpg",
				"hoi@gmail.com"));
		this.rumka = userRepository.save(
			new User(5000000L, "럼카", "https://s3.ap-northeast-2.amazonaws.com/user/rumka.jpg",
				"rumka@gmail.com"));
		this.admin = userRepository.save(
			new User(6000000L, "관리자", "https://s3.ap-northeast-2.amazonaws.com/user/admin.jpg",
				"admin@gmail.com", Authority.ADMIN));
	}

	private void initFollowingUserData() {
		this.forky.follow(this.hoi);
		this.forky.follow(this.rumka);
		this.hoi.follow(this.forky);
		this.hoi.follow(this.rumka);
		this.forky = userRepository.save(this.forky);
		this.hoi = userRepository.save(this.hoi);
		this.rumka = userRepository.save(this.rumka);
	}

	private void initUserAuthorizationData() {
		Date now = new Date();
		Date accessTokenExpiredTime = new Date(now.getTime() + 1800000);

		this.kukimValidAuthorizationHeader = generator.validAuthorizationHeader(kukim,
			accessTokenExpiredTime);
		this.jayValidAuthorizationHeader = generator.validAuthorizationHeader(jay,
			accessTokenExpiredTime);
		this.followSendUserValidAuthorizationHeader = generator.validAuthorizationHeader(forky,
			accessTokenExpiredTime);
		this.followAcceptUserValidAuthorizationHeader = generator.validAuthorizationHeader(hoi,
			accessTokenExpiredTime);
		this.kukimRefreshToken = generator.refreshToken(kukim, new Date(now.getTime() + 8000000));
		this.kukimExpiredRefreshToken = generator.refreshToken(kukim,
			new Date(now.getTime() - 1000));
		this.kukimInvalidRefreshToken = kukimRefreshToken + "invalid";
	}

	private void initReviewData() {
		this.kukimPublicReview = reviewRepository.save(new Review(this.kukim.getId(),
			this.store.getId(),
			"Test review contents",
			3.f,
			"public",
			LocalDate.of(2022, 1, 1),
			List.of("tag1", "tag2"),
			List.of("https://s3.ap-northeast-2.amazonaws.com/sikdorak/test.jpg")));

		this.followAcceptUserPublicReview = reviewRepository.save(new Review(this.hoi.getId(),
			this.store.getId(),
			"전체 공개된 리뷰 게시물",
			3.f,
			"public",
			LocalDate.of(2022, 1, 1),
			List.of("tag1", "tag2"),
			List.of("https://s3.ap-northeast-2.amazonaws.com/sikdorak/test.jpg")));

		this.followAcceptUserProtectedReview = reviewRepository.save(new Review(this.hoi.getId(),
			this.store.getId(),
			"친구 공개된 리뷰 게시물",
			3.f,
			"protected",
			LocalDate.of(2022, 1, 1),
			List.of("tag1", "tag2"),
			List.of("https://s3.ap-northeast-2.amazonaws.com/sikdorak/test.jpg")));

		this.followAcceptUserPrivateReview = reviewRepository.save(new Review(this.hoi.getId(),
			this.store.getId(),
			"비공개된 리뷰 게시물",
			3.f,
			"private",
			LocalDate.of(2022, 1, 1),
			List.of("tag1", "tag2"),
			List.of("https://s3.ap-northeast-2.amazonaws.com/sikdorak/test.jpg")));
	}

	private void postReviews() {
		for (int i = 0; i < 30; i++) {
			String visibility = i % 2 == 0 ? "public" : "protected";

			reviewRepository.save(new Review(this.forky.getId(),
				this.store.getId(),
				"전체 공개된 리뷰 게시물 " + i,
				3.f,
				visibility,
				LocalDate.of(2022, 1, 1),
				List.of("tag1", "tag2"),
				List.of("https://s3.ap-northeast-2.amazonaws.com/sikdorak/test.jpg")));
		}
	}

	private void likeReview() {
		followAcceptUserPublicReview.like(kukim);
		followAcceptUserProtectedReview.like(kukim);
		reviewRepository.save(followAcceptUserPublicReview);
		reviewRepository.save(followAcceptUserProtectedReview);
	}
}
