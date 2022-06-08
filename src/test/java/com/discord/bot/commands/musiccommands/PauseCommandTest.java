package com.discord.bot.commands.musiccommands;

import com.discord.bot.audioplayer.GuildMusicManager;
import com.discord.bot.commands.ISlashCommand;
import com.discord.bot.service.RestService;
import com.discord.bot.service.TrackService;
import com.discord.bot.service.audioplayer.PlayerManagerService;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.Map;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PauseCommandTest {
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

    /*
     * Purpose: Verify that PauseCommand works properly
     * Input: playerManagerService.pauseTrackForTest(event), verify(event)
     * Expected: Check that event is used a total of 2 times in operate() and pauseTrackForTest(event).
     */
    @Test
    public void operateTest() {
        PlayerManagerService playerManagerService = new PlayerManagerService(trackService, restService);
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getGuild().getAudioManager()).thenReturn(audioManager);
        playerManagerService.pauseTrackForTest(event);
        verify(event, times(2)).getGuild();
    }

}