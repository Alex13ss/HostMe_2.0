package com.softserve.edu.dto;

import com.softserve.edu.model.Request;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.exceptions.RequestCannotSendException;

public interface RequestDtoUtil {

	public Request formRequestModel(long endDate,long beginDate,Status status,int hostingId,String userLogin,String notes) throws RequestCannotSendException;

}
