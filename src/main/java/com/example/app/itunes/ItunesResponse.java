package com.example.app.itunes;

import java.util.List;

public record ItunesResponse(Integer resultCount, List<ItunesResult> results){
}
