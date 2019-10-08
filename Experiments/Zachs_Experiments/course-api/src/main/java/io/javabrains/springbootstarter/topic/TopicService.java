package io.javabrains.springbootstarter.topic;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService 
{
	@Autowired
	private TopicRepository topicRepository;
	
	private List<Topic> topics = new ArrayList<>(Arrays.asList(
			new Topic(1, "spring Framework", "Spring Framework Description"),
			new Topic(2, "Core Java", "Core Java Framework Description"),
			new Topic(3, "JavaScript", "JavaScript Description")));
	
	
	public List<Topic> getAllTopics()
	{
		//return topics;
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}
	
	public Topic getTopic(int id)
	{
		return topics.stream().filter(t -> t.getId() == id).findFirst().get();
	}
	
	public void addTopic(Topic topic)
	{
		topicRepository.save(topic);
	}
	
	public void updateTopic(int id, Topic topic)
	{
		for(int i = 0; i < topics.size(); i++)
		{
			Topic t = topics.get(i);
			if(t.getId() == id)
			{
				topics.set(i, topic);
				return;
			}
		}
	}
	
	public void deleteTopic(int id)
	{
		topics.removeIf(t -> t.getId() == id);
	}
}
