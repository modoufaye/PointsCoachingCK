package com.chb.dao;

import com.chb.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("select p from Point p where lower(p.coach.nomCoach)=:x")
    public List<Point> listPoint(@Param("x")String username);

    @Query("select p from Point p where p.codePoint=:x")
    public Point consulterPointCode(@Param("x")Long codePoint);
}
