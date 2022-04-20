package com.alphasegroup.attendancetracker.Services;

import java.net.URI;
import java.net.http.HttpResponse;
import java.io.IOException;


public interface qrCodeService {
    HttpResponse<byte[]> generate(URI uri) throws IOException, InterruptedException;
    
}
