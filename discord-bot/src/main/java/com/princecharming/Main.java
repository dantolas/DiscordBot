package com.princecharming;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Scanner;


public class Main{
    public static void main(String[] args) throws LoginException, InterruptedException {

        try{
            //My personal token, use this if need be
            //OTc2MDAxMDIxMTk1MjA2NzA2.GPs1s6.T0RIv2Y9M12j01ePHjVGM2-1bQ6NMky9E7jMwY
            System.out.println("| My personal token, use this if need be |");
            System.out.println("OTc2MDAxMDIxMTk1MjA2NzA2.GPs1s6.T0RIv2Y9M12j01ePHjVGM2-1bQ6NMky9E7jMwY");
            System.out.println("------------------------------------------------");
            System.out.println("Please enter your bot token: ");
            String token = new Scanner(System.in).next();
            token = token.replaceAll("\\s+","");



            //region<Launching the bot>
            JDA bot = JDABuilder.createDefault(token)
                    .enableCache(CacheFlag.VOICE_STATE)
                    .setActivity(Activity.playing(Constants.BOT_ACTIVITY))
                    .enableIntents(GatewayIntent.DIRECT_MESSAGE_REACTIONS,GatewayIntent.DIRECT_MESSAGES,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MESSAGE_REACTIONS)
                    .addEventListeners(new Listener())
                    .build().awaitReady();

            System.out.println("Project working directory"+System.getProperty("user.dir"));
            System.out.println("---------------------------------");
            System.out.println("The bot can be interacted with on this server for testing purposes: https://discord.gg/w95pPmQk (link expires after 7 days)");
            //endregion
        }catch (LoginException e){
            System.out.println("Oh no! Login error detected. The token provided is propably invalid.");
        }catch (InterruptedException e){
            System.out.println("Oh no! Interrupted exception detected.");
            e.printStackTrace();
        }






    }

}
