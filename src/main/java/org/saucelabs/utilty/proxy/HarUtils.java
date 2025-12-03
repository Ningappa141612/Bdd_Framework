package org.saucelabs.utilty.proxy;


import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import java.util.List;
import java.util.stream.Collectors;

public class HarUtils
{

    public static List<HarEntry> filterByUrl(Har har, String urlContains)
    {
        return har.getLog().getEntries()
                .stream()
                .filter(e -> e.getRequest().getUrl().contains(urlContains))
                .collect(Collectors.toList());
    }

    public static HarEntry getLatest(List<HarEntry> entries)
    {
        if (entries.isEmpty()) return null;
        return entries.get(entries.size() - 1);
    }
}
