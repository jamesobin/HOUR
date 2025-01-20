package com.jamesobin.hour.lecture.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.lecture.domain.Lecture;
import com.jamesobin.hour.lecture.dto.LectureDTO;
import com.jamesobin.hour.lecture.dto.PeriodDTO;
import com.jamesobin.hour.lecture.repository.LectureRepository;

@Service
public class PeriodDTOService {
	
	private LectureRepository lectureRepository;
	
	public PeriodDTOService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}

	public List<PeriodDTO> getPeriodList(int timetableId) {
		List<Lecture> lectureList = lectureRepository.findByTimetableId(timetableId);
		
		List<LectureDTO> firstPeriodlectureDTOList = new ArrayList<>(Collections.nCopies(5, null));
		List<LectureDTO> secondPeriodlectureDTOList = new ArrayList<>(Collections.nCopies(5, null));
		List<LectureDTO> thirdPeriodlectureDTOList = new ArrayList<>(Collections.nCopies(5, null));
		List<LectureDTO> fourthPeriodlectureDTOList = new ArrayList<>(Collections.nCopies(5, null));
		List<LectureDTO> fifthPeriodlectureDTOList = new ArrayList<>(Collections.nCopies(5, null));
		List<LectureDTO> sixthPeriodlectureDTOList = new ArrayList<>(Collections.nCopies(5, null));
		List<LectureDTO> seventhPeriodlectureDTOList = new ArrayList<>(Collections.nCopies(5, null));
		
		List<PeriodDTO> periodDTOList = new ArrayList<>();

		for(Lecture lecture:lectureList) {
			if(lecture.getStartTime().equals("09:00")) {
				LectureDTO firstLectureDTO = LectureDTO.builder()
						.lectureId(lecture.getId())
						.userId(lecture.getUserId())
						.lectureName(lecture.getLectureName())
						.professorName(lecture.getProfessorName())
						.classRoom(lecture.getClassRoom())
						.credit(lecture.getCredit())
						.day(lecture.getDay())
						.startTime(lecture.getStartTime())
						.endTime(lecture.getEndTime())
						.build();
				
				if(firstLectureDTO.getDay().equals("월")) {
					firstPeriodlectureDTOList.set(0, firstLectureDTO);
				} else if(firstLectureDTO.getDay().equals("화")) {
					firstPeriodlectureDTOList.set(1, firstLectureDTO);
				} else if(firstLectureDTO.getDay().equals("수")) {
					firstPeriodlectureDTOList.set(2, firstLectureDTO);
				} else if(firstLectureDTO.getDay().equals("목")) {
					firstPeriodlectureDTOList.set(3, firstLectureDTO);
				} else if(firstLectureDTO.getDay().equals("금")) {
					firstPeriodlectureDTOList.set(4, firstLectureDTO);
				}
			} 	
			if(lecture.getStartTime().equals("10:30") || lecture.getEndTime().equals("12:00")) {
				LectureDTO secondLectureDTO = LectureDTO.builder()
						.lectureId(lecture.getId())
						.userId(lecture.getUserId())
						.lectureName(lecture.getLectureName())
						.professorName(lecture.getProfessorName())
						.classRoom(lecture.getClassRoom())
						.credit(lecture.getCredit())
						.day(lecture.getDay())
						.startTime(lecture.getStartTime())
						.endTime(lecture.getEndTime())
						.build();
				
				if(secondLectureDTO.getDay().equals("월")) {
					secondPeriodlectureDTOList.set(0, secondLectureDTO);
				} else if(secondLectureDTO.getDay().equals("화")) {
					secondPeriodlectureDTOList.set(1, secondLectureDTO);
				} else if(secondLectureDTO.getDay().equals("수")) {
					secondPeriodlectureDTOList.set(2, secondLectureDTO);
				} else if(secondLectureDTO.getDay().equals("목")) {
					secondPeriodlectureDTOList.set(3, secondLectureDTO);
				} else if(secondLectureDTO.getDay().equals("금")) {
					secondPeriodlectureDTOList.set(4, secondLectureDTO);
				}
			}
			if(lecture.getStartTime().equals("12:00") || lecture.getEndTime().equals("13:30")) {
				LectureDTO thirdLectureDTO = LectureDTO.builder()
						.lectureId(lecture.getId())
						.userId(lecture.getUserId())
						.lectureName(lecture.getLectureName())
						.professorName(lecture.getProfessorName())
						.classRoom(lecture.getClassRoom())
						.credit(lecture.getCredit())
						.day(lecture.getDay())
						.startTime(lecture.getStartTime())
						.endTime(lecture.getEndTime())
						.build();
				
				if(thirdLectureDTO.getDay().equals("월")) {
					thirdPeriodlectureDTOList.set(0, thirdLectureDTO);
				} else if(thirdLectureDTO.getDay().equals("화")) {
					thirdPeriodlectureDTOList.set(1, thirdLectureDTO);
				} else if(thirdLectureDTO.getDay().equals("수")) {
					thirdPeriodlectureDTOList.set(2, thirdLectureDTO);
				} else if(thirdLectureDTO.getDay().equals("목")) {
					thirdPeriodlectureDTOList.set(3, thirdLectureDTO);
				} else if(thirdLectureDTO.getDay().equals("금")) {
					thirdPeriodlectureDTOList.set(4, thirdLectureDTO);
				}
			}
			if(lecture.getStartTime().equals("13:30") || lecture.getEndTime().equals("15:00")) {
				LectureDTO fourthLectureDTO = LectureDTO.builder()
						.lectureId(lecture.getId())
						.userId(lecture.getUserId())
						.lectureName(lecture.getLectureName())
						.professorName(lecture.getProfessorName())
						.classRoom(lecture.getClassRoom())
						.credit(lecture.getCredit())
						.day(lecture.getDay())
						.startTime(lecture.getStartTime())
						.endTime(lecture.getEndTime())
						.build();
				
				if(fourthLectureDTO.getDay().equals("월")) {
					fourthPeriodlectureDTOList.set(0, fourthLectureDTO);
				} else if(fourthLectureDTO.getDay().equals("화")) {
					fourthPeriodlectureDTOList.set(1, fourthLectureDTO);
				} else if(fourthLectureDTO.getDay().equals("수")) {
					fourthPeriodlectureDTOList.set(2, fourthLectureDTO);
				} else if(fourthLectureDTO.getDay().equals("목")) {
					fourthPeriodlectureDTOList.set(3, fourthLectureDTO);
				} else if(fourthLectureDTO.getDay().equals("금")) {
					fourthPeriodlectureDTOList.set(4, fourthLectureDTO);
				}
			}
			if(lecture.getStartTime().equals("15:00") || lecture.getEndTime().equals("16:30")) {
				LectureDTO fifthLectureDTO = LectureDTO.builder()
						.lectureId(lecture.getId())
						.userId(lecture.getUserId())
						.lectureName(lecture.getLectureName())
						.professorName(lecture.getProfessorName())
						.classRoom(lecture.getClassRoom())
						.credit(lecture.getCredit())
						.day(lecture.getDay())
						.startTime(lecture.getStartTime())
						.endTime(lecture.getEndTime())
						.build();
				
				if(fifthLectureDTO.getDay().equals("월")) {
					fifthPeriodlectureDTOList.set(0, fifthLectureDTO);
				} else if(fifthLectureDTO.getDay().equals("화")) {
					fifthPeriodlectureDTOList.set(1, fifthLectureDTO);
				} else if(fifthLectureDTO.getDay().equals("수")) {
					fifthPeriodlectureDTOList.set(2, fifthLectureDTO);
				} else if(fifthLectureDTO.getDay().equals("목")) {
					fifthPeriodlectureDTOList.set(3, fifthLectureDTO);
				} else if(fifthLectureDTO.getDay().equals("금")) {
					fifthPeriodlectureDTOList.set(4, fifthLectureDTO);
				}
			}
			if(lecture.getStartTime().equals("16:30") || lecture.getEndTime().equals("18:00")) {
				LectureDTO sixthLectureDTO = LectureDTO.builder()
						.lectureId(lecture.getId())
						.userId(lecture.getUserId())
						.lectureName(lecture.getLectureName())
						.professorName(lecture.getProfessorName())
						.classRoom(lecture.getClassRoom())
						.credit(lecture.getCredit())
						.day(lecture.getDay())
						.startTime(lecture.getStartTime())
						.endTime(lecture.getEndTime())
						.build();
				
				if(sixthLectureDTO.getDay().equals("월")) {
					sixthPeriodlectureDTOList.set(0, sixthLectureDTO);
				} else if(sixthLectureDTO.getDay().equals("화")) {
					sixthPeriodlectureDTOList.set(1, sixthLectureDTO);
				} else if(sixthLectureDTO.getDay().equals("수")) {
					sixthPeriodlectureDTOList.set(2, sixthLectureDTO);
				} else if(sixthLectureDTO.getDay().equals("목")) {
					sixthPeriodlectureDTOList.set(3, sixthLectureDTO);
				} else if(sixthLectureDTO.getDay().equals("금")) {
					sixthPeriodlectureDTOList.set(4, sixthLectureDTO);
				}
			}
			if(lecture.getStartTime().equals("18:00") || lecture.getEndTime().equals("19:30")) {
				LectureDTO seventhLectureDTO = LectureDTO.builder()
						.lectureId(lecture.getId())
						.userId(lecture.getUserId())
						.lectureName(lecture.getLectureName())
						.professorName(lecture.getProfessorName())
						.classRoom(lecture.getClassRoom())
						.credit(lecture.getCredit())
						.day(lecture.getDay())
						.startTime(lecture.getStartTime())
						.endTime(lecture.getEndTime())
						.build();
				
				if(seventhLectureDTO.getDay().equals("월")) {
					seventhPeriodlectureDTOList.set(0, seventhLectureDTO);
				} else if(seventhLectureDTO.getDay().equals("화")) {
					seventhPeriodlectureDTOList.set(1, seventhLectureDTO);
				} else if(seventhLectureDTO.getDay().equals("수")) {
					seventhPeriodlectureDTOList.set(2, seventhLectureDTO);
				} else if(seventhLectureDTO.getDay().equals("목")) {
					seventhPeriodlectureDTOList.set(3, seventhLectureDTO);
				} else if(seventhLectureDTO.getDay().equals("금")) {
					seventhPeriodlectureDTOList.set(4, seventhLectureDTO);
				}
			}
		}
		
		PeriodDTO periodDTO = PeriodDTO.builder().period("1교시 09:00~10:30").lectureList(firstPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("2교시 10:30~12:00").lectureList(secondPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("3교시 12:00~13:30").lectureList(thirdPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("4교시 13:30~15:00").lectureList(fourthPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("5교시 15:00~16:30").lectureList(fifthPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("6교시 16:30~18:00").lectureList(sixthPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("7교시 18:00~19:30").lectureList(seventhPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		return periodDTOList;
	}

}
