package dev.surya.labs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.surya.labs.entity.Content;

public interface ContentRepository extends JpaRepository<Content, Long>{

}
