package org.controller;

import com.opencsv.CSVReader;
import org.common.JDBCConnect;
import org.dao.TravelDAO;
import org.dto.TravelDTO;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

public class Controller {
    private TravelDAO dao = new TravelDAO();


    public boolean importFile(String resourceName, boolean hasHeader) {
        try (InputStream is = getClass().getResourceAsStream("/" + resourceName);
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {

            if (hasHeader) {
                reader.readNext(); // 첫 줄 스킵
            }

            String[] row;

            while ((row = reader.readNext()) != null) {
                TravelDTO dto = mapRowToDto(row);
                int result = dao.insert(dto);

                if (result > 0) {
                    System.out.println(dto.getNo() + " : 삽입 성공");
                } else {
                    System.out.println(dto.getNo() + " : 삽입 실패 및 프로그램 중단");
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    public TravelDTO mapRowToDto(String[] row) {
        TravelDTO dto = new TravelDTO();

        dto.setNo(trimOrNull(row[0]));
        dto.setDistrict(trimOrNull(row[1]));
        dto.setTitle(trimOrNull(row[2]));
        dto.setDescription(trimOrNull(row[3]));
        dto.setAddress(trimOrNull(row[4]));
        dto.setPhone(trimOrNull(row[5]));

        return dto;
    }

    private String trimOrNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;  // 빈칸을 null로 처리하고 싶으면 이렇게
        // 만약 빈칸도 "" 그대로 저장하고 싶으면 그냥 return s.trim();
    }
}
