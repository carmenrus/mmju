<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Member_message_m extends MY_Model {
	
	protected $table = 'member_message';
	
	function __construct() {
		parent :: __construct();
	}
}
