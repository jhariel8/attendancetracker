package com.alphasegroup.attendancetracker.Services;

import java.net.http.HttpResponse;
import java.io.IOException;


public interface qrCodeService {
    HttpResponse<byte[]> generate(String uri) throws IOException, InterruptedException;
    
}
