package com.restapi.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiVO {
	private String id;
	private String nm;
	private Date brdt;
	
	private String mbl_no;
	private String insp_rsvt_no;
	private String insp_addr;
	private String insp_dt;	
}
