package com.example.YTProj.YT2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.YTProj.YT2.Model.Yt2;

@Repository
public interface Yt2Repository extends JpaRepository<Yt2, Long> {
}
