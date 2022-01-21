package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.PaymentDto;
import com.btk.academia.rentACar.business.requests.paymentRequests.CreatePaymentRequest;

import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

import java.util.List;

public interface PaymentService {

    Result add(CreatePaymentRequest createPaymentRequest,Boolean isSaveUserInfo, String promotionCode);
    DataResult<List<PaymentDto>> getByRentalId(Integer rentalId);
}
