package com.example.YTProj.YT.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.example.YTProj.YT.Model.*;

@Repository
public interface YtRepository extends JpaRepository<Yt, Long> {
}
