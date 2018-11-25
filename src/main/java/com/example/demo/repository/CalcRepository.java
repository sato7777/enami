package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Calc;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CalcRepository extends JpaRepository<Calc, Long> {}
