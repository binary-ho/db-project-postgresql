package com.gov.appDemo.service;

import com.gov.appDemo.domain.DegreeMap;
import com.gov.appDemo.domain.Student;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class ParsingService {
	
    public List<Student> getStudentsList() {
        return parseStudents(parseStudentElements());
    }
    
    private List<ElementDto> parseStudentElements() {
        Document document = this.getHTMLDocument("https://apl.hongik.ac.kr/lecture/dbms");

        String attributeKey = "style";
        String attributeValue = "list-style-type: square";
        Elements elementsByAttribute = document.getElementsByAttribute(attributeKey);
        List<ElementDto> studentElements = new ArrayList<>();

        int idx = 0;
        for (Element element : elementsByAttribute) {
            if (!element.attr(attributeKey).contains(attributeValue)) {
                continue;
            }
            Elements liTagList = element.getElementsByTag("li");
            for (Element li : liTagList) {
                studentElements.add(new ElementDto(li, idx));
            }
            idx++;
        }
        return studentElements;
    }
    
    private List<Student> parseStudents(List<ElementDto> elements) {
        List<Student> students = new ArrayList<>();
        DegreeMap degreeMap = new DegreeMap();
        elements.forEach(element -> {
            String[] fieldArray = element.getElement().text().split(",");
            Student student = new Student();
            student.setName(fieldArray[0].trim());
            student.setEmail(fieldArray[1].trim());
            student.setGraduation(fieldArray[2].trim());
            student.setDegree(degreeMap.get(element.getIdx()));
            students.add(student);
        });
        return students;
    }
    
    private Document getHTMLDocument(String url) {
        try {
            return Jsoup.connect("https://apl.hongik.ac.kr/lecture/dbms").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Data
    static class ElementDto {
        
    	int idx;
    	Element element;
        ElementDto(Element element, int idx) {
            this.element = element;
            this.idx = idx;
        }
    }
}