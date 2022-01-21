package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.InvoiceDto;

import com.btk.academia.rentACar.core.utilities.results.DataResult;



public interface InvoiceService {
    DataResult<InvoiceDto> get(Integer rentalId);
}
