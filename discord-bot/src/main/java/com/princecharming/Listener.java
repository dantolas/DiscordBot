package com.princecharming;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//This class is the bot's eyes and ears, listening for all events that are overriden from ListenerAdapter
public class Listener extends ListenerAdapter {

    public final CommandManager cm = new CommandManager();


    //Prints to console when bot is ready and on how many guilds he is available
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println(event.getJDA().getSelfUser().getName()+" is online");
        System.out.println("Available on `"+event.getGuildAvailableCount()+"` guilds out of `"+event.getGuildTotalCount()+"`");
    }

    //Whenever someone joins, they get a welcome messages
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        event.getGuild().getTextChannelsByName("general",true).forEach(textChannel -> textChannel.sendMessage("Ayo new member dropped ~ "+event.getMember().getUser().getName() +". Welcome !").queue());
    }
    //Text manager handles message reactions
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        cm.tm.handle(event);

    }
    //Command manager or text manager handles messages
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();


        if (event.getAuthor().isBot()){
            return;
        }
        System.out.println(message);
        if(message.startsWith(Constants.BOT_COMMAND_PREFIX) || message.endsWith(Constants.BOT_SECRET_COMMAND_AFTERFIX)){
            System.out.println("Listener gave the message to commandManager ~ System time:" + dtf.format(now));
            cm.handle(event);
        }else {
            System.out.println("Listener gave the message to textManager ~ System time:"   + dtf.format(now));
            cm.tm.handle(event);
        }

    }
}
