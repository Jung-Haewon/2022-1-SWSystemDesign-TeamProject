package com.discord.bot.commands.musiccommands.Fails;

import com.discord.bot.commands.musiccommands.ChannelValidation;
import com.discord.bot.commands.musiccommands.QueueCommand;
import com.discord.bot.service.audioplayer.PlayerManagerService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(QueueCommand.class)
public class QueueCommandTest {
    SlashCommandInteractionEvent event = mock(SlashCommandInteractionEvent.class);
    ChannelValidation channelValidation = mock(ChannelValidation.class);
    PlayerManagerService playerManagerService = mock(PlayerManagerService.class);

    /**
     * Purpose : Verify Queue Command return Error message well
     * Input : isValidState(false)
     * Expected : return "The queue is currently empty"
     */
    @Test
    public void errorMessageTest1() throws Exception {
        when(playerManagerService.isQueueNotEmpty(any(SlashCommandInteractionEvent.class))).thenReturn(false);
        QueueCommand queueCommand = new QueueCommand(playerManagerService, channelValidation);

        assertEquals("The queue is currently empty", queueCommand.ErrorMessageTest(event));
    }

    /**
     * Purpose : Verify Queue Command return Error message well
     * Input : isValidState(True)
     * Expected : return "The queue is currently empty"
     */
    @Test
    public void errorMessageTest2() throws Exception {
        when(playerManagerService.isQueueNotEmpty(any(SlashCommandInteractionEvent.class))).thenReturn(true);
        QueueCommand queueCommand = new QueueCommand(playerManagerService, channelValidation);

        assertEquals(null, queueCommand.ErrorMessageTest(event));
    }

    /*
    @Test
    public void testSomething() {
        QueueCommand queueCommand = new QueueCommand(playerManagerService, channelValidation);
        String errorMessage = "The queue is currently empty";

        when(queueCommand, "execute", any(SlashCommandInteractionEvent.class)).thenReturn(false);
        when(queueCommand, "isValidState", any(SlashCommandInteractionEvent.class)).thenReturn(false);
        //when(queueCommand, "getFailDescription", null).thenReturn(false);
        assertEquals(false, queueCommand.isValid(event));

        //verifyPrivate(event).invoke("getFailDescription", any());
        //verifyPrivate(event).invoke("replyEmbeds", any(EmbedBuilder.class));
        //assertEquals(errorMessage, failQueueEmptyStrategy.getFailDescription());
    }
    */
}