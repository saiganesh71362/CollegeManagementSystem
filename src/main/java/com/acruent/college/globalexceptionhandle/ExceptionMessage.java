package com.acruent.college.globalexceptionhandle;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionMessage
{
	private Date date;
	private String message;
	private String details;

}
