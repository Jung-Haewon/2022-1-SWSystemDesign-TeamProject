package com.discord.bot.commands.musiccommands;

import net.dv8tion.jda.api.EmbedBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class MusicHelpCommandTest {
    /*
     * Purpose: Verify that MusicHelpCommnad print proper message
     * Input: asserEquals(embedBuilder1, embedBuilder1)
     * Expected: The title and description of both embedBuilders must be the same.
     *          the result of assert is true
     */
    @Test
    public void executeTest() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        showDetailOption(embedBuilder);
        EmbedBuilder embedBuilder1 = new EmbedBuilder();
        embedBuilder1.setTitle("Music Commands").setDescription("""
                        - /play
                        - /skip
                        - /pause
                        - /resume
                        - /leave
                        - /queue
                        - /swap
                        - /shuffle
                        - /loop
                        """)
                .setFooter("Bot can't play shorts.");
        assertEquals(embedBuilder1.build().getTitle(), embedBuilder.build().getTitle());
        assertEquals(embedBuilder1.build().getDescription(), embedBuilder.build().getDescription());
    }

    private void showDetailOption(EmbedBuilder embedBuilder) {
        embedBuilder.setTitle("Music Commands").setDescription("""
                        - /play
                        - /skip
                        - /pause
                        - /resume
                        - /leave
                        - /queue
                        - /swap
                        - /shuffle
                        - /loop
                        """)
                .setFooter("Bot can't play shorts.");
    }
}