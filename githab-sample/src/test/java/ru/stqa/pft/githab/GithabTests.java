package ru.stqa.pft.githab;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;


public class GithabTests {
  @Test
  public void testCommits() throws IOException {
  Github github = new RtGithub("1996ac2da05f7524ed024996b0e5dae3d5c41399 ");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("julja83", "java_test")).commits();
    for(RepoCommit commit:commits.iterate(new ImmutableMap.Builder<String, String>().build())){
     System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
