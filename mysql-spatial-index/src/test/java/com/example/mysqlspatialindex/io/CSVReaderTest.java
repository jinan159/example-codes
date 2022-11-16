package com.example.mysqlspatialindex.io;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CSVReaderTest {

    @Nested
    @DisplayName("생성자는")
    class Constructor {

        @Test
        void 존재하지_않는_csv_를_읽으면_RuntimeException_예외가_발생한다 () {
            String filePath = "not_existing.csv";
            assertThrows(RuntimeException.class, () -> new CSVReader(filePath));
        }
    }

    @Nested
    @DisplayName("read 메소드는")
    class Read {
        @Test
        void 정상적인_csv_는_내용을_정확히_읽어온다() {
            // given
            String filePath = getClasspathFilePath("test.csv");
            Map<String, String> csvRow;

            // when
            try (CSVReader csvReader = new CSVReader(filePath)) {
                csvRow = csvReader.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // then
            assertAll(
                () -> assertTrue(csvRow.containsKey("col1")),
                () -> assertTrue(csvRow.containsKey("col2")),
                () -> assertTrue(csvRow.containsKey("col3")),
                () -> assertEquals("텍스트1", csvRow.get("col1")),
                () -> assertEquals("123", csvRow.get("col2")),
                () -> assertEquals("abc", csvRow.get("col3"))
            );
        }

        @Test
        void row_가_비어있는_csv_를_읽으면_RuntimeException_예외가_발생한다 () {
            // given
            String filePath = getClasspathFilePath("row_empty.csv");

            try (CSVReader csvReader = new CSVReader(filePath)) {
                // then
                assertThrows(IllegalStateException.class, csvReader::read);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }

    private String getClasspathFilePath(String fileName) {
        return Objects.requireNonNull(this.getClass()
                .getClassLoader()
                .getResource(fileName))
            .getPath();
    }
}