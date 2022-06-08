package com.discord.bot.commands.musiccommands;

import com.discord.bot.service.RestService;
import com.discord.bot.service.TrackService;
import com.discord.bot.service.audioplayer.PlayerManagerService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResumeCommandTest {
    @Mock
    SlashCommandInteractionEvent event= mock(SlashCommandInteractionEvent.class);
    @Mock
    TrackService trackService = mock(TrackService.class);
    @Mock
    RestService restService = mock(RestService.class);
    @Mock
    Guild guild = mock(Guild.class);
    @Mock
    AudioManager audioManager = mock(AudioManager.class);
    @Mock
    EmbedBuilder embedBuilder = mock(EmbedBuilder.class);
    /*
     * Purpose: Verify that ResumeCommand works properly
     * Input: playerManagerService.resumeTrackForTest(event), verify(event)
     * Expected: Check that event is used a total of 2 times in operate() and resumeTrackForTest(event).
     */
    @Test
    public void operateTest() {
        PlayerManagerService playerManagerService = new PlayerManagerService(trackService, restService);
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getGuild().getAudioManager()).thenReturn(audioManager);
        playerManagerService.resumeTrackForTest(event);
        verify(event, times(2)).getGuild();
    }
}