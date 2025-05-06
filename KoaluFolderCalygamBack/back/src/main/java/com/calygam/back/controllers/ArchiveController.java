package com.calygam.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calygam.back.repositories.TrailRepository;
import com.calygam.back.utils.MakeUploadAndDownloadArchive;

@RestController
@RequestMapping("file")
public class ArchiveController {
	  @Autowired
	    private MakeUploadAndDownloadArchive uploadService;

	    @Autowired
	    private TrailRepository trailRepository;

	    @GetMapping("/read/{archiveName}")
	    public ResponseEntity<Resource> downloadTrailArchive(@PathVariable String archiveName) {
	        return uploadService.downloadArchive(archiveName, trailRepository);
	    }
}
