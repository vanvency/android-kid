/* 
   Copyright 2011 Rolf Kulemann, Pascal Bockhorn

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.androidkid.logging;

import java.io.Serializable;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import android.util.Log;

/**
 * Appender for {@link Log}
 *
 * @author Rolf Kulemann, Pascal Bockhorn
 */
@Plugin(name = "LogCat", category = "Core", elementType = "appender", printObject = true)
public final class LogCatAppender extends AbstractAppender {

    protected Layout tagLayout;

    public LogCatAppender(String name, final Layout tagLayout, final Layout layout, final Filter filter) {
        super(name, filter, layout);
        this.tagLayout = tagLayout;
    }

    public LogCatAppender(final Layout tagLayout, final Layout layout) {
        this("LogCat", tagLayout, layout, null);
    }

    @PluginFactory
    public static LogCatAppender createAppender(
            @PluginElement("TagLayout") Layout<? extends Serializable> taglayout,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter,
            @PluginAttribute("name") final String name) {
        if (name == null) {
            LOGGER.error("No name provided for ConsoleAppender");
            return null;
        }
        if (taglayout == null) {
            taglayout = PatternLayout.createLayout("%c", null, null, null, true, true, null, null);
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new LogCatAppender(name, taglayout, layout, filter);
    }

    @Override
    public void append(final LogEvent event) {
        switch (event.getLevel().getStandardLevel()) {
            case TRACE:
                if (event.getThrown() != null) {
                    Log.v(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString(), event.getThrown());
                } else {
                    Log.v(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString());
                }
                break;
            case DEBUG:
                if (event.getThrown() != null) {
                    Log.d(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString(), event.getThrown());
                } else {
                    Log.d(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString());
                }
                break;
            case INFO:
                if (event.getThrown() != null) {
                    Log.i(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString(), event.getThrown());
                } else {
                    Log.i(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString());
                }
                break;
            case WARN:
                if (event.getThrown() != null) {
                    Log.w(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString(), event.getThrown());
                } else {
                    Log.w(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString());
                }
                break;
            case ERROR:
                if (event.getThrown() != null) {
                    Log.e(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString(), event.getThrown());
                } else {
                    Log.e(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString());
                }
                break;
            case FATAL:
                if (event.getThrown() != null) {
                    Log.wtf(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString(), event.getThrown());
                } else {
                    Log.wtf(getTagLayout().toSerializable(event).toString(), getLayout().toSerializable(event).toString());
                }
                break;
        }
    }

    public Layout getTagLayout() {
        return tagLayout;
    }

    public void setTagLayout(final Layout tagLayout) {
        this.tagLayout = tagLayout;
    }
}