package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll
import org.telegram.telegrambots.meta.api.methods.send.*

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.File

class WCCBot : TelegramLongPollingBot() {

    val welcome = """
        Olá\, amigo\! Bem\-vindo ao meu JavaBot\!
        Vamos acessar algumas folhas de dicas Dev\?
        Digite o comando abaixo para dar início\:
        \/star \- para começar
        \/git \- para folha de dicas do Git
        \/kotlin \- para folha de dicas do Kotlin
        \/apirest \- para folha de dicas de API Rest
        \/motivacao \- para assistir video motivacional
        \/info \- para mais informações
    """.trimMargin()

    override fun getBotUsername(): String {
        //return bot username
        // If bot username is @HelloKotlinBot, it must return
        return "JavaBot"
    }

    override fun getBotToken(): String {
        // Return bot token from BotFather
        return "1957004094:AAHopzJN_Msee3-d3qW0oAx_8Nrxwj4Gzf4"
    }

    override fun onUpdateReceived(update: Update?) {
        // We check if the update has a message and the message has text
        val nameSender = update?.message?.from?.firstName
        val chatId = update?.message?.chatId.toString()
        val messageCommand = update?.message?.text

        try {

            if(messageCommand == "/start") {
                val sendDocument = SendDocument().apply {
                    this.chatId = chatId
                    this.caption = getMessage(messageCommand)
                    this.document = InputFile().setMedia("https://developers.giphy.com/branch/master/static/api-512d36c09662682717108a38bbb5c57d.gif")
                    this.parseMode = "MarkdownV2"
                }
                execute(sendDocument)
            }

            if(messageCommand == "/git") {
                val sendDocument = SendDocument().apply {
                    this.chatId = chatId
                    this.caption = getMessage(messageCommand)
                    this.document = InputFile().setMedia("https://education.github.com/git-cheat-sheet-education.pdf")
                    this.parseMode = "MarkdownV2"
                }
                execute(sendDocument)
            }

            if(messageCommand == "/kotlin") {
                val sendDocument = SendDocument().apply {
                    this.chatId = chatId
                    this.caption = getMessage(messageCommand)
                    this.document = InputFile().setMedia("https://kt.academy/Kotlin_Cheat_Sheet.pdf")
                    this.parseMode = "MarkdownV2"
                }
                execute(sendDocument)
            }

            if(messageCommand == "/apirest") {
                val sendDocument = SendDocument().apply {
                    this.chatId = chatId
                    this.caption = getMessage(messageCommand)
                    this.document = InputFile().setMedia("https://restdb.io/media/restdb-cheat-sheet.pdf")
                    this.parseMode = "MarkdownV2"
                }
                execute(sendDocument)
            }

            if(messageCommand == "/motivacao") {
                val sendDocument = SendVideo().apply {
                    this.chatId = chatId
                    this.caption = getMessage(messageCommand)
                    this.video = InputFile().setMedia(File("/home/endy/wcc6-kotlin/bot-telegram/wcc-kotlin-telegram-bot/music/vouserfederal_video_1500943304570.mp4"))
                    this.parseMode = "MarkdownV2"
                }
                execute(sendDocument)
            }
//
//            if(messageCommand == "/quizOne") {
//                val sendDocument = SendPoll().apply {
//                    this.chatId = chatId
//                    this.question = "Java or Kotlin?"
//                    this.options = listOf("Java", "Kotlin")
//                    this.correctOptionId = 1
//                    this.isAnonymous = false
//                }
//                execute(sendDocument)
//            }

        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }

    private fun getMessage(command: String?): String? = when(command) {
        "/info" -> "Não há informações"
        "/start" -> welcome
        "/git" -> "Git"
        "/kotlin" -> "Kotlin"
        "/apirest" -> "API Rest"
        "/motivacao" -> "Video Motivacional"
        else -> EmojiParser.parseToUnicode("tente novamente :cry:")
    }
}

