package com.softserve.edu.dto;

import com.softserve.edu.entity.Status;
import com.softserve.edu.entity.exceptions.RequestCannotSendException;
import com.softserve.edu.entity.Request;

public interface RequestDtoUtil {

	public Request formRequestModel(long endDate,long beginDate,Status status,int hostingId,String userLogin,String notes) throws RequestCannotSendException;

}
