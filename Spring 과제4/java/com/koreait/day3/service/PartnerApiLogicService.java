package com.koreait.day3.service;

import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.entity.Partner;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.Pagination;
import com.koreait.day3.model.network.request.PartnerApiRequest;
import com.koreait.day3.model.network.response.PartnerApiResponse;
import com.koreait.day3.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    private final PartnerRepository partnerRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Partner partner = Partner.builder()
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .build();
        Partner newPartner = partnerRepository.save(partner);
        return Header.OK(response(newPartner));

    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(partners -> response(partners))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Optional<Partner> optional = partnerRepository.findById(partnerApiRequest.getId());
        return optional.map(partner -> {
                    partner.setName(partnerApiRequest.getName());
                    partner.setStatus(partnerApiRequest.getStatus());
                    partner.setAddress(partnerApiRequest.getAddress());
                    partner.setCallCenter(partnerApiRequest.getCallCenter());
                    partner.setBusinessNumber(partnerApiRequest.getBusinessNumber());
                    partner.setCeoName(partnerApiRequest.getCeoName());
                    return partner;
                }).map(partner -> partnerRepository.save(partner))
                .map(partner->response(partner))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> optional = partnerRepository.findById(id);
        return optional.map(partner ->{
            partnerRepository.delete(partner);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public PartnerApiResponse response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .regDate(partner.getRegDate())
                .build();
        return partnerApiResponse;
    }
    public Header<List<PartnerApiResponse>> search(Pageable pageable){
        Page<Partner> partners = baseRepository.findAll(pageable);
        List<PartnerApiResponse> partnerApiResponseList = partners.stream()
                .map(partner->response(partner))
                .collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(partners.getTotalPages())
                .totalElements(partners.getTotalElements())
                .currentPage(partners.getNumber())
                .currentElements(partners.getNumberOfElements())
                .build();
        return Header.OK(partnerApiResponseList, pagination);
    }
}
