package com.alphasegroup.attendancetracker.Services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class qrCodeServiceImpl implements qrCodeService {

    @Override
    public HttpResponse<byte[]> generate(String uri) throws IOException, InterruptedException {
        URI fin = null;
		try {
			fin = new URI(uri);
		}
		catch(Exception e) {}
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(fin).build();
        HttpResponse<byte[]> responseOfByteArray = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        return responseOfByteArray;
    }

}
