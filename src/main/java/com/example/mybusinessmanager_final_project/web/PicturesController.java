package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.binding.PictureBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.PictureEntity;
import com.example.mybusinessmanager_final_project.model.service.PictureServiceModel;
import com.example.mybusinessmanager_final_project.model.view.PictureViewModel;
import com.example.mybusinessmanager_final_project.repository.PictureRepository;
import com.example.mybusinessmanager_final_project.service.CloudinaryImage;
import com.example.mybusinessmanager_final_project.service.CloudinaryService;
import com.example.mybusinessmanager_final_project.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PicturesController {

    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public PicturesController(CloudinaryService cloudinaryService,
                              PictureRepository pictureRepository, PictureService pictureService, ModelMapper modelMapper) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        return new PictureEntity().
                setPublicId(uploaded.getPublicId()).
                setTitle(title).
                setUrl(uploaded.getUrl());
    }

   @PostMapping("/reports/{reportId}/pictures/add")
    public String newPicture(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long reportId,
            @ModelAttribute("pictureBindingModel") PictureBindingModel pictureBindingModel
    ) throws IOException {

        var picture = createPictureEntity(pictureBindingModel.getPicture(),
                pictureBindingModel.getTitle());


        PictureServiceModel pictureServiceModel =
                modelMapper.map(pictureBindingModel, PictureServiceModel.class);
        pictureServiceModel.setTitle(picture.getTitle())
                .setUrl(picture.getUrl())
                .setPublicId(picture.getPublicId());
        pictureServiceModel.setCreator(principal.getUsername());
        pictureServiceModel.setReportId(reportId);

        PictureViewModel newPicture =
                pictureService.addPicture(pictureServiceModel);

        URI locationOfNewComment =
                URI.create(String.format("/reports/%s/pictures/%s", reportId, newPicture.getPictureId()));

        PictureEntity pictureEntity = modelMapper.map(newPicture, PictureEntity.class);
        pictureRepository.save(pictureEntity);


        return reportId + "report-details";
    }

    @Transactional
    @DeleteMapping("/api/{reportId}/pictures/{picturesId}/delete")
    public String delete(@RequestParam("public_id") String publicId) {
        if (cloudinaryService.delete(publicId)) {
            pictureRepository.deleteAllByPublicId(publicId);
        }
        return "redirect:api/{reportId}/pictures/all";
    }

    @GetMapping("/reports/{reportId}/pictures")
    public ResponseEntity<List<PictureViewModel>> getPictures(
            @PathVariable Long reportId,
            Principal principal, Model model, @ModelAttribute("pictureBindingModel") PictureBindingModel pictureBindingModel
    ) {
        List<PictureViewModel> pictures = pictureRepository.
                findAll().
                stream().
                map(this::asViewModel).
                collect(Collectors.toList());
        model.addAttribute("pictures", pictures);

        return ResponseEntity.ok(
                pictureService.getPictures(reportId));
    }

    private PictureViewModel asViewModel(PictureEntity picture) {
        return new PictureViewModel().
                setPublicId(picture.getPublicId()).
                setTitle(picture.getTitle()).
                setUrl(picture.getUrl()).
                setUser(picture.getAuthor().getUsername());

    }
}