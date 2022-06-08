package com.discord.bot.commands.musiccommands.Fails;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
public class FailDescriptionStrategyTest {
    private FailDescriptionStrategy failDescriptionStrategy;

    /**
     * Purpose : Verify FailQueueEmptyStrategy returns the correct error message
     * Input : getFailDescription()
     * Expected : return "The queue is currently empty"
     */
    @Test
    public void queueFailTest() {
        failDescriptionStrategy = new FailQueueEmptyStrategy();
        assertEquals("The queue is currently empty", failDescriptionStrategy.getFailDescription());
    }

    /**
     * Purpose : Verify ChannelFailStrategy returns the correct error message
     * Input : getFailDescription()
     * Expected : return "Please be in a same voice channel as bot."
     */
    @Test
    public void channelFailTest() {
        failDescriptionStrategy = new ChannelFailStrategy();
        assertEquals("Please be in a same voice channel as bot.", failDescriptionStrategy.getFailDescription());
    }
}