package com.dylibso.chicory.compiler.internal;

import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.runtime.Machine;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public final class CompilerResult {

    private final Function<Instance, Machine> machineFactory;
    private final Map<String, byte[]> classBytes;
    private final Set<Integer> interpretedFunctions;
    private final List<Integer> functionGroupOfFuncId;

    public CompilerResult(
            Function<Instance, Machine> machineFactory,
            Map<String, byte[]> classBytes,
            Set<Integer> interpretedFunctions,
            List<Integer> functionGroupOfFuncId) {
        this.machineFactory = machineFactory;
        this.classBytes = classBytes;
        this.interpretedFunctions = Set.copyOf(interpretedFunctions);
        this.functionGroupOfFuncId = List.copyOf(functionGroupOfFuncId);
    }

    public Function<Instance, Machine> machineFactory() {
        return machineFactory;
    }

    public Map<String, byte[]> classBytes() {
        return classBytes;
    }

    public Set<Integer> interpretedFunctions() {
        return interpretedFunctions;
    }

    public List<Integer> functionGroupOfFuncId() {
        return functionGroupOfFuncId;
    }
}
