package com.planet.dashboard.service;

import com.planet.dashboard.entity.CrawlingComponent;
import com.planet.dashboard.entity.CrawlingSite;
import com.planet.dashboard.entity.StudyDto;
import com.planet.dashboard.repository.CrawlingSiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrawlingService implements CrawlStudyInfo {

    private final CrawlingSiteRepository crawlingSiteRepository;

    @Override
    public StudyDto retrieveStudyInfo() {
        return  crawlingSiteRepository.findAll().stream()
                .flatMap((site)->{
                    //TODO 스터디 공고 수집 -> JSOUP
                    List<CrawlingComponent> components = site.getComponents();
                   return components.stream().map(StudyDto::new).collect(Collectors.toList());
                });
    }
}
