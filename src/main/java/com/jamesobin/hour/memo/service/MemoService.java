package com.jamesobin.hour.memo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jamesobin.hour.common.FileManager;
import com.jamesobin.hour.memo.domain.Memo;
import com.jamesobin.hour.memo.repository.MemoRepository;

@Service
public class MemoService {

	private MemoRepository memoRepository;
	
	public MemoService(MemoRepository memoRepository) {
		this.memoRepository = memoRepository;
	}

	public boolean addMemo(int userId, int lectureId, String title, String contents, MultipartFile file) {
		String imagePath = FileManager.saveFile(userId, file);
		
		Memo memo = Memo.builder()
		.userId(userId)
		.lectureId(lectureId)
		.title(title)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		try {
			memoRepository.save(memo);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public List<Memo> getPostList(int userId) {
		return memoRepository.findByUserId(userId);
	}
	
	public Memo getMemo(int id) {
		Optional<Memo> optionalMemo = memoRepository.findById(id);
		
		return optionalMemo.orElse(null);
	}

	
}
