package com.softserve.edu.dto;

import com.softserve.edu.entity.Request;
import com.softserve.edu.entity.Status;
import com.softserve.edu.entity.exceptions.RequestCannotSendException;

public interface RequestDtoUtil {

	public Request formRequestModel(long endDate,long beginDate,Status status,int hostingId,String userLogin,String notes) throws RequestCannotSendException;

}
