package branch;

import java.util.*;
import java.io.*;

public class BranchManager {
    private List<Branch> branches = new ArrayList<>();

    public BranchManager() {
        loadBranches();
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
        saveBranches();
    }

    public void updateBranch(Branch branch) {
        // TODO: Implement updating an existing branch
        saveBranches();
    }

    public void removeBranch(String id) {
        branches.removeIf(branch -> branch.getId().equals(id));
        saveBranches();
    }

    public List<Branch> getBranches() {
        return Collections.unmodifiableList(branches);
    }

    private void loadBranches() {
        // TODO: Implement loading branches from CSV
    }

    private void saveBranches() {
        // TODO: Implement saving branches to CSV
    }
}
