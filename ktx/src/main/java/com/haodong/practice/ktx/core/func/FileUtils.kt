package com.haodong.practice.ktx.core.func

import com.haodong.practice.ktx.ext.canListFiles
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.text.DecimalFormat

/**
 * created by linghaoDo on 2020/7/16
 * description:
 *
 * version:
 */
/**
 * Return the file size, include all sub files
 */
fun getFolderSize(file: File): Long {
    var total = 0L
    for (subFile in file.listFiles()) {
        total += if (subFile.isFile) subFile.length()
        else getFolderSize(subFile)
    }
    return total
}

/**
 * Return the formatted file size, like "4.78 GB"
 * @param unit 1000 or 1024, default to 1000
 */
fun getFormatFileSize(size: Long, unit: Int = 1000): String {
    val formatter = DecimalFormat("####.00")
    return when {
        size < 0 -> "0 B"
        size < unit -> "$size B"
        size < unit * unit -> "${formatter.format(size.toDouble() / unit)} KB"
        size < unit * unit * unit -> "${formatter.format(size.toDouble() / unit / unit)} MB"
        else -> "${formatter.format(size.toDouble() / unit / unit / unit)} GB"
    }
}

/**
 * Return all subFile in the folder
 */
fun getAllSubFile(folder: File): Array<File> {
    var fileList: Array<File> = arrayOf()
    if (!folder.canListFiles) return fileList
    for (subFile in folder.listFiles())
        fileList = if (subFile.isFile) fileList.plus(subFile)
        else fileList.plus(getAllSubFile(subFile))
    return fileList
}

/**
 * copy the [sourceFile] to the [destFile], only for file, not for folder
 * @param overwrite if the destFile is exist, whether to overwrite it
 */
fun copyFile(sourceFile: File, destFile: File, overwride: Boolean, func: ((file: File, i: Int) -> Unit)? = null) {
    if (!sourceFile.exists()) return
    if (destFile.exists()) {
        val stillExists = if (!overwride) true else !destFile.delete()
        if (stillExists) {
            return
        }
    }
    if (!destFile.exists()) {
        destFile.createNewFile()
    }
    val inputStream = FileInputStream(sourceFile)
    val outputStream = FileOutputStream(destFile)
    val iChannel = inputStream.channel
    val oChannel = outputStream.channel

    val totalSize = sourceFile.length()
    val buffer = ByteBuffer.allocate(1024)
    var hasRead = 0f;
    var progress = -1
    while (true) {
        buffer.clear()
        val read = iChannel.read(buffer)
        if (read == -1) {
            return
        }
        buffer.limit(buffer.position())
        buffer.position(0)
        oChannel.write(buffer)
        hasRead += read
        func?.let {
            {
                val newProgress = ((hasRead / totalSize) * 100).toInt()
                if (progress != newProgress) {
                    progress = newProgress
                    it(sourceFile, progress)
                }
            }
        }
        inputStream.close()
        outputStream.close()

    }
}
/**
 * copy the [sourceFolder] to the [destFolder]
 * @param overwrite if the destFile is exist, whether to overwrite it
 */
fun copyFolder(sourceFolder: File, destFile: File, overwride: Boolean, func: ((file: File, i: Int) -> Unit)? = null) {
    if (!sourceFolder.exists()) return

    if (!destFile.exists()) {
        val result = destFile.mkdirs()
        if (!result) {
            return
        }
    }
    for (subFile in sourceFolder.listFiles()) {
        if (subFile.isDirectory) {
            copyFolder(subFile, File("${destFile.path}${File.separator}${subFile.name}"), overwride, func)
        } else {
            copyFile(subFile, File(destFile, subFile.name), overwride, func)
        }
    }

}

