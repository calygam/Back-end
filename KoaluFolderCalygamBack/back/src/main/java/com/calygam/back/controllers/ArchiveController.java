package com.calygam.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.repositories.PetOutfitRepository;
import com.calygam.back.repositories.ProgressRepository;
import com.calygam.back.repositories.SubmissionsRepository;
import com.calygam.back.repositories.TrailRepository;
import com.calygam.back.utils.MakeUploadAndDownloadArchive;

@RestController
@RequestMapping("file")
public class ArchiveController {
	  @Autowired
	    private MakeUploadAndDownloadArchive uploadService;

	    @Autowired
	    private TrailRepository trailRepository;
	    @Autowired
	    private ProgressRepository progressRepository;
	    
	    @Autowired
	    private SubmissionsRepository submissionsRepository;
	    
	    @Autowired
	    private PetOutfitRepository petOutfitRepository;

	    @GetMapping("/read/{archiveName:.+}")
	    public ResponseEntity<Resource> downloadTrailArchive(@PathVariable String archiveName) {
	        System.out.println("🔍 Arquivo requisitado: " + archiveName);
	        return uploadService.downloadArchive(archiveName, trailRepository);
	    }
	    
	   

	    @GetMapping("/read/submission/{archiveName:.+}")
	    public ResponseEntity<Resource> downloadProgressArchive(@PathVariable String archiveName) {
	        System.out.println("🔍 Arquivo requisitado: " + archiveName);
	        return uploadService.downloadArchive(archiveName, submissionsRepository);
	    }
	    
	    @GetMapping("/read/skins/{archiveName:.+}")
	    public ResponseEntity<Resource> downloadSkinArchive(@PathVariable String archiveName) {
	        System.out.println("🔍 Arquivo requisitado: " + archiveName);
	        return uploadService.downloadArchive(archiveName, petOutfitRepository);
	    }
}
