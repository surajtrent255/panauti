INSERT INTO family_member (
	`family_id`,
	`full_name`,
	`relation_id`,
	`age`,
	`gender_id`,
	`marital_status`,
	`qualification_id`,
	`occupation`,
	`has_voter_id`,
	`migration`,
	`health_status`,
	`member_id`,
	`date_of_birth`
) VALUE
(
	:mainId,
	:name,
	:relation,
	:age,
	:gender,
	:maritalStatus,
	:education,
	:occupation,
	:voterCard,
	:address,
	:healthCondition,
	:memberId,
	:dateOfBirth
);