package com.jamesobin.hour.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hour.memo.domain.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer> {

	public List<Memo> findByUserId(int userId);
	
}
