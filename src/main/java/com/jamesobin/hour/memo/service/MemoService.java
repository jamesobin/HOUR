package com.jamesobin.hour.memo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.memo.domain.Memo;
import com.jamesobin.hour.memo.repository.MemoRepository;
import com.jamesobin.hour.timetable.service.TimetableService;

@Service
public class MemoService {

	private MemoRepository memoRepository;
	private TimetableService timetableService;
	
	public MemoService(MemoRepository memoRepository, TimetableService timetableService) {
		this.memoRepository = memoRepository;
		this.timetableService = timetableService;
	}

	public boolean addMemo(int userId, int timetableId, String lectureName, String title, String contents) {		
		Memo memo = Memo.builder()
		.userId(userId)
		.timetableId(timetableId)
		.lectureName(lectureName)
		.title(title)
		.contents(contents)
		.build();
		
		try {
			memoRepository.save(memo);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public List<Memo> getMemoList(int userId) {
		return memoRepository.findByUserId(userId);
	}
	
	public Memo getMemo(int id) {
		Optional<Memo> optionalMemo = memoRepository.findById(id);
		
		return optionalMemo.orElse(null);
	}

	public boolean deleteMemo(int id) {
		Optional<Memo> optionalMemo = memoRepository.findById(id);
		
		if(optionalMemo.isPresent()) {
			Memo memo = optionalMemo.get();
			
			memoRepository.delete(memo);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateMemo(int id, int timetableId, String lectureName, String title, String contents) {
		Optional<Memo> optionalMemo = memoRepository.findById(id);
		
		if(optionalMemo.isPresent()) {
			Memo memo = optionalMemo.get();
			
			memo = memo.toBuilder()
					.timetableId(timetableId)
					.lectureName(lectureName)
					.title(title)
					.contents(contents)
					.build();
			
			try {
				memoRepository.save(memo);
				return true;
			} catch(Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String getTermString(int id) {
		Optional<Memo> optionalMemo = memoRepository.findById(id);

		String termString = null;
		
		if(optionalMemo.isPresent()) {
			Memo memo = optionalMemo.get();
			termString = timetableService.getTerm(memo.getTimetableId());
		}
		
		return termString;
	}
	
}
