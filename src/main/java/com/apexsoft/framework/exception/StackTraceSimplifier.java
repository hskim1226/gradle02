package com.apexsoft.framework.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hanmomhanda on 15. 2. 21.
 */
public class StackTraceSimplifier {

    public static List<StackTraceElement> getEffectiveStackTraces(
            StackTraceElement[] stackTraces, String pkgId) {
        List<StackTraceElement> effectiveStackTraces = new ArrayList<StackTraceElement>();
        for (StackTraceElement stackTrace : stackTraces) {
            if (stackTrace.getClassName().startsWith(pkgId)) {
                effectiveStackTraces.add(stackTrace);
            }
        }
        return effectiveStackTraces;
    }

    public static String getSimpleCallStack(
            StackTraceElement[] stackTraces, String pkgId, boolean isEffectiveList) {
        StringBuilder simpleCallStack = new StringBuilder();
        List<StackTraceElement> effectiveList = null;
        String direction = "\n\t> ";
        if (!isEffectiveList) {
            effectiveList = getEffectiveStackTraces(stackTraces, pkgId);
        } else {
            effectiveList = Arrays.asList(stackTraces);
        }
        int len = effectiveList.size();
        while (len-- > 0) {
            simpleCallStack.append(direction).append(effectiveList.get(len));
        }
        return simpleCallStack.toString();
    }
}
