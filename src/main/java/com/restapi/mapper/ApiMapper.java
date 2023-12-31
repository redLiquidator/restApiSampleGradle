package com.restapi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.restapi.model.ApiVO;

@Mapper
public interface ApiMapper {
	
	public ApiVO test();
	
	//회원 정보및 예약 정보 조회
	public ApiVO getUserinfoById(String id);
	//회원 정보만 조회
	public ApiVO getOnlyUserinfoById(String id);	
	//예약 정보만 조회
	public ApiVO getReservationInfo(String insp_rsvt_no);
	
	//검사예약
	public void updateExamNo(ApiVO ApiVO);
	public void insertReservation(ApiVO ApiVO);
	
	//예약수정
	public void updateReservation(ApiVO apiVO);
	
	//예약삭제
	public void deleteReservation(ApiVO apiVO);


	

}
