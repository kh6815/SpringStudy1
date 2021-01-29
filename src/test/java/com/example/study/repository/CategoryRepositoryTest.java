package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assertions.assertNotNull(newCategory);
        assertThat(newCategory.getType()).isEqualTo(type);
        assertThat(newCategory.getTitle()).isEqualTo(title);
    }

    @Test
    public void read(){
        String type = "COMPUTER";

        Optional<Category> optionalCategory = categoryRepository.findByType("COMPUTER");

        optionalCategory.ifPresent(category -> {
            assertThat(category.getType()).isEqualTo(type);
            System.out.println("category.getId() = " + category.getId());
            System.out.println("category.getType() = " + category.getType());
            System.out.println("category.getTitle() = " + category.getTitle());
        });
    }
}
