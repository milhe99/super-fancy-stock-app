package fi.jyu.superfancystockapp.scheduler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class ScheduledFetchPrice {
    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        fetchLastTradedPrice();
    }
    @Scheduled(cron = "@hourly") // Scheduled to fetch last traded price hourly
    public void onScheduled() {
        fetchLastTradedPrice();
    }

    private static float lastPrice;
    public void fetchLastTradedPrice() {
        try {
            String curlCommand = "curl https://api.marketdata.app/v1/stocks/quotes/AAPL/?columns=last";
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", curlCommand);
            Process process = processBuilder.start();

            String output;

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                output = reader.readLine();
                lastPrice = Float.parseFloat(StringUtils.substringBetween(output, "[", "]"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            process.destroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static float getLastPrice() {
        return lastPrice;
    }
}
