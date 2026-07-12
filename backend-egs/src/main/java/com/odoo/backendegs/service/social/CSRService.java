package com.odoo.backendegs.service.social;

import com.odoo.backendegs.dto.request.CreateCSRActivityRequest;
import com.odoo.backendegs.dto.request.JoinCSRActivityRequest;
import com.odoo.backendegs.dto.response.CSRActivityResponse;
import com.odoo.backendegs.dto.response.ParticipationResponse;
import com.odoo.backendegs.entity.department.Department;
import com.odoo.backendegs.entity.social.CSRActivity;
import com.odoo.backendegs.entity.social.Participation;
import com.odoo.backendegs.enums.social.CSRActivityStatus;
import com.odoo.backendegs.enums.social.ParticipationStatus;
import com.odoo.backendegs.exception.exceptions.ResourceNotFoundException;
import com.odoo.backendegs.repo.department.DepartmentRepo;
import com.odoo.backendegs.repo.social.CSRActivityRepository;
import com.odoo.backendegs.repo.social.ParticipationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CSRService {

    private final CSRActivityRepository csrActivityRepo;
    private final ParticipationRepository participationRepo;
    private final DepartmentRepo departmentRepo;
    private final XPService xpService;
    private final SocialScoreService socialScoreService;
    private final ModelMapper modelMapper;

    public CSRService(CSRActivityRepository csrActivityRepo,
                          ParticipationRepository participationRepo,
                          DepartmentRepo departmentRepo,
                          XPService xpService,
                          SocialScoreService socialScoreService,
                          ModelMapper modelMapper) {

        this.csrActivityRepo = csrActivityRepo;
        this.participationRepo = participationRepo;
        this.departmentRepo = departmentRepo;
        this.xpService = xpService;
        this.socialScoreService = socialScoreService;
        this.modelMapper = modelMapper;
    }

    public CSRActivityResponse createActivity(CreateCSRActivityRequest request) {

        CSRActivity activity = modelMapper.map(request, CSRActivity.class);

        activity.setStatus(CSRActivityStatus.UPCOMING);

        return modelMapper.map(
                csrActivityRepo.save(activity),
                CSRActivityResponse.class
        );
    }

    public ParticipationResponse joinActivity(JoinCSRActivityRequest request) {

        Department department = departmentRepo.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found"));

        CSRActivity activity = csrActivityRepo.findById(request.getCsrActivityId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("CSR Activity not found"));

        Participation participation = Participation.builder()
                .employeeName(request.getEmployeeName())
                .department(department)
                .csrActivity(activity)
                .proofUrl(request.getProofUrl())
                .remarks(request.getRemarks())
                .joinedAt(LocalDateTime.now())
                .status(ParticipationStatus.PENDING)
                .build();

        return modelMapper.map(
                participationRepo.save(participation),
                ParticipationResponse.class
        );
    }

    public ParticipationResponse approveParticipation(Long participationId) {

        Participation participation = participationRepo.findById(participationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Participation not found"));

        participation.setStatus(ParticipationStatus.APPROVED);
        participation.setReviewedAt(LocalDateTime.now());

        participationRepo.save(participation);

        xpService.awardXP(participationId);

        socialScoreService.updateDepartmentSocialScore(
                participation.getDepartment().getId()
        );

        return modelMapper.map(
                participation,
                ParticipationResponse.class
        );
    }

    public ParticipationResponse rejectParticipation(Long participationId) {

        Participation participation = participationRepo.findById(participationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Participation not found"));

        participation.setStatus(ParticipationStatus.REJECTED);
        participation.setReviewedAt(LocalDateTime.now());

        participationRepo.save(participation);

        return modelMapper.map(
                participation,
                ParticipationResponse.class
        );
    }

    public List<CSRActivityResponse> getAllActivities() {

        return csrActivityRepo.findAll()
                .stream()
                .map(activity ->
                        modelMapper.map(activity, CSRActivityResponse.class))
                .toList();
    }
}