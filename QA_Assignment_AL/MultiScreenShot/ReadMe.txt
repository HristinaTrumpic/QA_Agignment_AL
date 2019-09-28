This tool takes screenshots and gives them different names by adding a number after given name.


@Test
	public void GoogleAbout() throws Exception {
		driver.findElement(By.linkText(Prop.getProperty("AboutLinkText"))).click();
		MultiScreenShot multiScreens = new MultiScreenShot("C:\\New\\","GoogleAbout");
		multiScreens.multiScreenShot(driver);
		driver.findElement(By.linkText("More about our philosophy")).click();
		multiScreens.multiScreenShot(driver);
	}