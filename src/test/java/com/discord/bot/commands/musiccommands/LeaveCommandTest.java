package com.discord.bot.commands.musiccommands;

import com.discord.bot.audioplayer.GuildMusicManager;
import com.discord.bot.audioplayer.TrackScheduler;
import com.discord.bot.service.RestService;
import com.discord.bot.service.TrackService;
import com.discord.bot.service.audioplayer.PlayerManagerService;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.empty;
import static reactor.core.publisher.Mono.when;

@RunWith(MockitoJUnitRunner.class)
public class LeaveCommandTest {
    @Mock
    SlashCommandInteractionEvent event = mock(SlashCommandInteractionEvent.class);
    @Mock
    TrackService trackService = mock(TrackService.class);
    @Mock
    RestService restService = mock(RestService.class);
    @Mock
    Guild guild = mock(Guild.class);
    @Mock
    AudioManager audioManager = mock(AudioManager.class);

    @Mock
    TrackScheduler scheduler = mock(TrackScheduler.class);
    /*
     * Purpose: Verify that LeaveCommand works properly
     * Input: playerManagerService.leavePlayerForTest(event), verify(event)
     * Expected: Check evnet.getGuild() has been used 3 times
     * and schduler.queue is null
     */
    @Test
    public void operateTest() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        PlayerManagerService playerManagerService = new PlayerManagerService(trackService, restService);
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getGuild().getAudioManager()).thenReturn(audioManager);
        playerManagerService.leavePlayerForTest(event);
        verify(event, times(3)).getGuild();
        assertTrue(scheduler.queue == null);
    }
}