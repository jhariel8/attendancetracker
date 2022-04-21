package com.alphasegroup.attendancetracker.Controllers;

import com.alphasegroup.attendancetracker.Services.qrCodeService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

@Controller
public class qrCodeController {

    @Autowired
    private qrCodeService qrCodeService;

    @GetMapping("qr-code")
    public ResponseEntity<HttpResponse<byte[]>> getQrCode(URI uri) throws IOException, InterruptedException {

        HttpResponse<byte[]> qrImage = qrCodeService.generate(uri.toString());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
    }

}