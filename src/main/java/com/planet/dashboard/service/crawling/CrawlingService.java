package com.planet.dashboard.service.crawling;

import com.planet.dashboard.entity.CrawlingComponent;
import com.planet.dashboard.entity.CrawlingComponentResult;
import com.planet.dashboard.entity.CrawlingSite;
import com.planet.dashboard.repository.CrawlingComponentRepository;
import com.planet.dashboard.repository.CrawlingComponentResultRepository;
import com.planet.dashboard.repository.CrawlingSiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrawlingService implements CrawlStudyInfo , Runnable {

    private final CrawlingSiteRepository crawlingSiteRepository;
    private final CrawlingComponentRepository crawlingComponentRepository;
    private final CrawlingComponentResultRepository crawlingComponentResultRepository;

    @Override
    public void retrieveStudyInfo(int pageNum) throws IOException {
        List<CrawlingSite> sites = crawlingSiteRepository.findAll();
        for (CrawlingSite site : sites) {

            Document doc = Jsoup.connect(site.getPath() + "/community/studies?status=unrecruited&page="+pageNum ).get();
            Elements elements = doc.select(site.getCssPath());
                for (Element element : elements) {
                    if(isYesterDay(element)){
                        String id = getBoardId(element.attr("href"));
                        Document document = Jsoup.connect(site.getPath() + "/studies/" + id).get();
                        parseDocument(document,id);
                    }
                }
            }
        }
    }

    private boolean isYesterDay(Element element) {
        return element.select(".question__info-footer").text().endsWith("1일 전");
    }

    private void parseDocument(Document document, String boardId) {
        List<CrawlingComponent> components = crawlingComponentRepository.findAll();
        for (CrawlingComponent component : components) {
            Elements value = document.select(component.getCssPath());
            if(component.getName().equals("content")){
                crawlingComponentResultRepository.save
                        (CrawlingComponentResult.builder()
                                .crawlingComponent(component)
                                .value(value.html())
                                .boardId(boardId)
                                .build()
                        );
            }else{
                crawlingComponentResultRepository.save(
                        CrawlingComponentResult.builder()
                                .crawlingComponent(component)
                                .value(value.text())
                                .boardId(boardId)
                                .build()
                );
            }
        }
    }

    @Override
    public void run() {
        log.info("crawling batch job 시작");
        try {
            retrieveStudyInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getBoardId(String url){
        int startIdx = url.indexOf("/", 1);
        return url.substring(startIdx+1);
    }

}
