package com.discord.bot.commands.musiccommands;

import com.discord.bot.commands.musiccommands.ChannelValid.IsBotAndUserInSameChannel;
import com.discord.bot.commands.musiccommands.ChannelValid.IsBotInVoiceChannel;
import com.discord.bot.commands.musiccommands.ChannelValid.IsUserInVoiceChannel;
import com.discord.bot.commands.musiccommands.ChannelValid.ValidStrategy;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChannelValidationTest {
    @Mock
    SlashCommandInteractionEvent event= mock(SlashCommandInteractionEvent.class);
    @Mock
    Guild guild = mock(Guild.class);
    @Mock
    Member member = mock(Member.class);
    @Mock
    GuildVoiceState guildVoiceState = mock(GuildVoiceState.class);
    @Mock
    User user = mock(User.class);
    @Mock
    AudioChannel audioChannel = mock(AudioChannel.class);
    private ValidStrategy validStrategy;

    /*
     * Purpose: Verify that the  IsBotAndUserInSameChannel strategy is created
     * Input: assertTrue(validStrategy instance of IsBotAndUserInSameChannel)
     * Expected: "true" if the correct strategy is assigned
     *           "false" otherwise
     */
    @Test
    public void setStrategyTest1() {
        this.validStrategy = new IsBotAndUserInSameChannel();
        assertTrue(validStrategy instanceof IsBotAndUserInSameChannel);
    }
    /*
     * Purpose: Verify that the  IsUserInVoiceChannelstrategy is created
     * Input: assertTrue(validStrategy instance of IsUserInVoiceChannel)
     * Expected: "true" if the correct strategy is assigned
     *           "false" otherwise
     */
    @Test
    public void setStrategyTest2() {
        this.validStrategy = new IsUserInVoiceChannel();
        assertTrue(validStrategy instanceof IsUserInVoiceChannel);
    }
    /*
     * Purpose: Verify that the  IsBotInVoiceChannel is created
     * Input: assertTrue(validStrategy instance of IsBotInVoiceChannel)
     * Expected: "true" if the correct strategy is assigned
     *           "false" otherwise
     */
    @Test
    public void setStrategyTest3() {
        this.validStrategy = new IsBotInVoiceChannel();
        assertTrue(validStrategy instanceof IsBotInVoiceChannel);
    }

    @Test
    public void testSetStrategy() {
    }
    /*
    * Purpose: Verify the behavior of strategy "IsBotInVoiceChannel"
    * Input: isValid(event), retrun inAudioChannel: true/false
    * Expected: "true" if the bot is in the voice channel,
    *           "false" otherwise
     */
    @Test
    public void isValidTest1() {
        this.validStrategy = new IsBotInVoiceChannel();
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getGuild().getSelfMember()).thenReturn(member);
        Mockito.when(event.getGuild().getSelfMember().getVoiceState()).thenReturn(guildVoiceState);
        Mockito.when(event.getGuild().getSelfMember().getVoiceState().inAudioChannel()).thenReturn(false);
        assertEquals(event.getGuild().getSelfMember().getVoiceState().inAudioChannel(), validStrategy.isValid(event));
        Mockito.when(event.getGuild().getSelfMember().getVoiceState().inAudioChannel()).thenReturn(true);
        assertEquals(event.getGuild().getSelfMember().getVoiceState().inAudioChannel(), validStrategy.isValid(event));
    }
    /*
     * Purpose: Verify the behavior of strategy "IsBotAndUserInSameChannel"
     * Input: isValid(event), retrun inAudioChannel: true/false
     * Expected: "true" if the bot and user in the same voice channel,
     *           "false" otherwise
     */
    @Test
    public void isValidTest2() {
        this.validStrategy = new IsBotAndUserInSameChannel();
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getGuild().getSelfMember()).thenReturn(member);
        Mockito.when(event.getGuild().getSelfMember().getVoiceState()).thenReturn(guildVoiceState);
        Mockito.when(event.getGuild().getSelfMember().getVoiceState().inAudioChannel()).thenReturn(false);
        assertEquals(event.getGuild().getSelfMember().getVoiceState().inAudioChannel(), validStrategy.isValid(event));

        Mockito.when(event.getMember()).thenReturn(member);
        Mockito.when(event.getMember().getVoiceState()).thenReturn(guildVoiceState);
        Mockito.when(event.getMember().getVoiceState().inAudioChannel()).thenReturn(false);
        assertEquals(event.getMember().getVoiceState().inAudioChannel(), validStrategy.isValid(event));
    }
    @Test
    public void isValidTest3(){
        this.validStrategy = new IsBotAndUserInSameChannel();
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getGuild().getSelfMember()).thenReturn(member);
        Mockito.when(event.getGuild().getSelfMember().getVoiceState()).thenReturn(guildVoiceState);

        Mockito.when(event.getMember()).thenReturn(member);
        Mockito.when(event.getMember().getVoiceState()).thenReturn(guildVoiceState);

        Mockito.when(event.getGuild().getSelfMember().getVoiceState().getChannel()).thenReturn(audioChannel);
        Mockito.when(event.getMember().getVoiceState().getChannel()).thenReturn(audioChannel);
        assertEquals(validStrategy.isValid(event), false);


        Mockito.when(guildVoiceState.inAudioChannel()).thenReturn(true);
        assertEquals(validStrategy.isValid(event), true);
    }
    /*
     * Purpose: Verify the behavior of strategy "IsUserInVoiceChannel"
     * Input: isValid(event), retrun inAudioChannel: true/false
     * Expected: "true" if the user in the voice channel,
     *           "false" otherwise
     */
    @Test
    public void isValidTest4() {
        this.validStrategy = new IsUserInVoiceChannel();
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getMember()).thenReturn(member);
        Mockito.when(event.getMember().getVoiceState()).thenReturn(guildVoiceState);
        Mockito.when(event.getMember().getVoiceState().inAudioChannel()).thenReturn(false);
        assertEquals(event.getMember().getVoiceState().inAudioChannel(), validStrategy.isValid(event));
        Mockito.when(event.getMember().getVoiceState().inAudioChannel()).thenReturn(true);
        assertEquals(event.getMember().getVoiceState().inAudioChannel(), validStrategy.isValid(event));
    }
}